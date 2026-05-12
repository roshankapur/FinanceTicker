const TICKERS = ['AAPL', 'MSFT', 'GOOGL', 'AMZN', 'META'];

// Build the table rows once on page load — they never get destroyed
function initTable() {
    const tableBody = document.getElementById('price-table-body');
    TICKERS.forEach(ticker => {
        const tr = document.createElement('tr');
        tr.id = `row-${ticker}`;
        tr.innerHTML = `
            <td>${ticker}</td>
            <td class="number" id="price-${ticker}">--</td>
        `;
        tableBody.appendChild(tr);
    });
}

// Only updates the text + class of existing cells — no DOM destruction
function updatePrices() {
    fetch('/api/stocks/refresh', { method: 'POST' })
        .then(response => response.json())
        .then(data => {
            data.forEach(stock => {
                const priceCell = document.getElementById(`price-${stock.ticker}`);
                if (!priceCell) return;

                priceCell.textContent = stock.current;

                // Update color class based on price vs previous close
                priceCell.classList.remove('price-up', 'price-down');
                if (stock.current > stock.previousClose) {
                    priceCell.classList.add('price-up');
                } else if (stock.current < stock.previousClose) {
                    priceCell.classList.add('price-down');
                }
            });
        })
        .catch(error => console.error('Error:', error));
}

// Build rows once, then poll for data
initTable();
updatePrices();
setInterval(updatePrices, 5000);
