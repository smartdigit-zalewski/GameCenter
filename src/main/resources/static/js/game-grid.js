// Create map to keep all ships before sending them to backend
let fleet = new Map();
// List of all grid fields with their IDs
let cellArray = [];
// Temp list to add cells to each ship
let shipArray = [];
// temp value to count ship cells
let tempVal = 1;

function drawGrid(type) {
    // GRID PROPERTIES
    let rows = 10;
    let cols = 10;
    let battleground = document.getElementById("battleground");
    // CREATE GRID USING BOOTSTRAP
    for(i=0; i < rows; i++) {
        let row = document.createElement("div")
        row.classList.add('row');
        //row.style.border ='0.2px solid black';
        battleground.appendChild(row);
        for(j=0; j < cols; j++) {
            // Create new div inside battleground and make it correct size
            let col = document.createElement("div");
            col.classList.add('col')
            col.id = 'c' + j + i;
            cellArray.push(col.id);
            col.style.backgroundColor = 'white';
            col.style.height = '60px';
            col.style.border = '0.2px solid black';
            row.appendChild(col);
        }
    }
}

// Once player choose Ship to set on grid add onClick to all fields
function addShipToFleet(shipLength) {
    cellArray.forEach( i => {
            let cell = document.getElementById(i);
            cell.removeAttribute("onClick");
            cell.onclick = function () {setShip(shipLength, i)};
        }
    )
}
// Remove onClick when all ship cells are filled
function removeOnClickFromGrid() {
    cellArray.forEach(i => {
        document.getElementById(i).removeAttribute("onClick");
    })
}

// Set color for chosen field and add its value to tempArray.
function setShip(shipLength, id) {
    let cell = document.getElementById(id);
    if(cell.style.backgroundColor === 'white'){
        cell.style.backgroundColor = '#30d196';
        handleClicks(shipLength, id);
    } else {
        cell.style.backgroundColor = 'white';
    }
}

function handleClicks(shipLength, id) {
    if (tempVal >= shipLength){
        shipArray.push(id);
        removeOnClickFromGrid();
        cellArray.forEach(i => {
            let cell = document.getElementById(i);
            cell.onclick =function () {if (!(confirm('All fields for this ship are set.'))) return false}
        })
        fleet.set('ship_' + shipLength, shipArray);
        tempVal = 1;
        shipArray = [];
    } else {
        shipArray.push(id);
        tempVal++;
    }
}

function checkFleetCompletion() {
    return fleet.size === 5;
}

function blockAllButtons() {

}

function showEnterGameButton() {

}

function persistFleet(gameId, playerId) {
    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    headers.append('Access-Control-Allow-Origin', 'http://localhost:8080');
    headers.append('Access-Control-Allow-Credentials', 'true');

    let url = 'http://localhost:8080/api/games/' + gameId + '/fleets'

    let fleetDTO = {
        'playerId' : playerId,
        'fleet' : {
            'ship_1' : fleet.get('ship_1'),
            'ship_2' : fleet.get('ship_2'),
            'ship_3' : fleet.get('ship_3'),
            'ship_4' : fleet.get('ship_4'),
            'ship_5' : fleet.get('ship_5')
        }
    };

    fetch(url, {
        crossDomain: true,
        method:'POST',
        headers:headers,
        body: JSON.stringify(fleetDTO)
    }).then(response => response.json())
        .then(response => {
            let responseStatus = response.response
            if(responseStatus === 'Success') {
                console.log("Successfully persisted fleet")
                blockAllButtons();
                showEnterGameButton();
            }
        })
}

function replacer(key, value) {
    if(value instanceof Map) {
        return {
            //dataType: 'Map',
            value: Array.from(value.entries())
        };
    }
    return value;
}