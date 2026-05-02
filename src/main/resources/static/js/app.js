let stompClient = null;

async function loadDashboard() {
    try {
        const accResponse = await fetch('/api/accounts');
        const accounts = await accResponse.json();

        const custResponse = await fetch('/api/customers');
        const customers = await custResponse.json();

        updateStats(accounts, customers);
        renderAccountsTable(accounts);
        updateDropdowns(accounts, customers);
    } catch (error) {
        console.error('Error loading dashboard:', error);
    }
}

function updateStats(accounts, customers) {
    document.getElementById('total-accounts').innerText = accounts.length;
    document.getElementById('total-customers').innerText = customers.length;
    
    const liquidity = accounts.reduce((sum, acc) => sum + acc.balance, 0);
    document.getElementById('total-liquidity').innerText = `$${liquidity.toLocaleString()}`;
}

function renderAccountsTable(accounts) {
    const tbody = document.getElementById('accounts-table');
    tbody.innerHTML = '';

    accounts.forEach(acc => {
        const row = `
            <tr>
                <td>${acc.id}</td>
                <td><strong>${acc.accountNumber}</strong></td>
                <td>${acc.customerId}</td>
                <td>$${acc.balance.toLocaleString()}</td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

function updateDropdowns(accounts, customers) {
    const customerSelect = document.getElementById('acc-cust-id');
    const fromSelect = document.getElementById('trans-from');
    const toSelect = document.getElementById('trans-to');

    // Populate Customer Select
    customerSelect.innerHTML = '<option value="">Select Customer</option>';
    customers.forEach(cust => {
        customerSelect.innerHTML += `<option value="${cust.id}">${cust.name} (ID: ${cust.id})</option>`;
    });

    // Populate Account Selects
    const accountOptions = '<option value="">Select Account</option>' + 
        accounts.map(acc => `<option value="${acc.id}">${acc.accountNumber} (Bal: $${acc.balance})</option>`).join('');
    
    fromSelect.innerHTML = accountOptions;
    toSelect.innerHTML = accountOptions;
}

// --- Form Actions ---

async function createCustomer() {
    const name = document.getElementById('cust-name').value;
    const email = document.getElementById('cust-email').value;

    const response = await fetch('/api/customers', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, email })
    });

    if (response.ok) {
        showNotification("CUSTOMER_REGISTERED");
        loadDashboard();
    }
}

async function openAccount() {
    const accountNumber = document.getElementById('acc-number').value;
    const customerId = document.getElementById('acc-cust-id').value;
    const balance = document.getElementById('acc-balance').value;

    const response = await fetch('/api/accounts', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ accountNumber, customerId, balance })
    });

    if (response.ok) {
        // Notification is already handled by WebSocket on server side
    } else {
        alert("Failed to open account. Check Customer ID.");
    }
}

async function transferMoney() {
    const fromAccountId = document.getElementById('trans-from').value;
    const toAccountId = document.getElementById('trans-to').value;
    const amount = document.getElementById('trans-amount').value;

    const response = await fetch('/api/transfers/internal', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ fromAccountId, toAccountId, amount })
    });

    if (response.ok) {
        // Notification is handled by WebSocket
    } else {
        alert("Transfer failed. Insufficient funds or invalid IDs.");
    }
}

// --- WebSocket Logic ---

function connectWebSocket() {
    const socket = new SockJS('/ws-bank');
    stompClient = Stomp.over(socket);
    
    // Disable debug logging to keep console clean
    stompClient.debug = null;

    stompClient.connect({}, function (frame) {
        console.log('Connected to WebSocket: ' + frame);
        
        stompClient.subscribe('/topic/updates', function (update) {
            console.log('Live Update Received:', update.body);
            // Re-load the dashboard data whenever a notification arrives
            loadDashboard();
            showNotification(update.body);
        });
    });
}

function showNotification(message) {
    // Simple toast notification
    const toast = document.createElement('div');
    toast.style.position = 'fixed';
    toast.style.bottom = '20px';
    toast.style.right = '20px';
    toast.style.background = '#2563eb';
    toast.style.color = 'white';
    toast.style.padding = '10px 20px';
    toast.style.borderRadius = '5px';
    toast.style.boxShadow = '0 2px 10px rgba(0,0,0,0.2)';
    toast.textContent = '📢 ' + message.replace(/_/g, ' ');
    
    document.body.appendChild(toast);
    
    setTimeout(() => {
        toast.style.opacity = '0';
        toast.style.transition = 'opacity 0.5s ease';
        setTimeout(() => toast.remove(), 500);
    }, 3000);
}

// Initial load
loadDashboard();
connectWebSocket();
