async function loadDashboard() {
    try {
        // Fetch Accounts
        const accResponse = await fetch('/api/accounts');
        const accounts = await accResponse.json();

        // Fetch Customers
        const custResponse = await fetch('/api/customers');
        const customers = await custResponse.json();

        updateStats(accounts, customers);
        renderAccountsTable(accounts);
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

// Initial load
loadDashboard();
