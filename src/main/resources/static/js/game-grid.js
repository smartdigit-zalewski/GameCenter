// Create map to keep all ships before sending them to backend
let fleet = new Map();
// List of all grid fields with their IDs
let cellArray = [];
// Temp list to add cells to each ship
let shipArray = [];
// temp value to count ship cells
let tempVal = 1;

function onLoad() {
    drawGrid();
    drawShipButtons();

}

function drawGrid() {
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

function drawShipButtons(){

    let NUMBER_OF_BUTTONS = 5;
    let container = document.getElementById('buttonsContainer');
    for(i=1;i <= NUMBER_OF_BUTTONS; i++ ){
        let button = document.createElement("button");
        button.onclick = function() {markShip(i)};
        button.classList.add('btn');
        button.classList.add('btn-danger');
        button.id = 'ship' + i;
        button.innerText = 'Ship ' + i;
        container.appendChild(button);
    }


}

// Once player choose Ship to set on grid add onClick to all fields
function markShip(shipLength) {
    console.log("Mark ship with length: " + shipLength);
    cellArray.forEach( i => {
            let cell = document.getElementById(i);
            cell.onclick = null;
            cell.style.backgroundColor = 'white';
            cell.onclick = function () {markField(shipLength, i)};
        }
    )
}

// Remove onClick when all ship cells are filled
function removeOnClickFromFields() {
    cellArray.forEach(i => {
        document.getElementById(i).onclick = null;
    })
}

// Set color for chosen field and add its value to tempArray.
function markField(shipLength, id) {
    let cell = document.getElementById(id);
    if(cell.style.backgroundColor === 'white'){
        console.log("Chosen field: " + cell.id + ", field color change: white -> green");
        cell.style.backgroundColor = 'green';
        handleClicks(shipLength, id);
        removeFieldFromArray(id);
        calculatePossibleFields(id)
    } else if (cell.style.backgroundColor === 'grey') {
        console.log("Chosen field is grey, user can't select it. Add warning")
        confirm('You can\'t select that field')
    } else {
        console.log("Selected previously selected field: NOT IMPLEMENTED YET")
        cell.style.backgroundColor = 'white';
    }
}
function removeFieldFromArray(id) {
    for(i=0; i < cellArray.length; i++) {
        if(cellArray[i] === id ) {
            cellArray.splice(i,1);
        }
    }
}

function handleClicks(shipLength, id) {
    console.log("Check if number of marked fields for ship is lesser than total ship length: shipLength: " + shipLength + ", marked fields: " + tempVal);
    if (tempVal >= shipLength){
        console.log("Last field marked")
        shipArray.push(id);
        console.log("Removing onClick function for grid and adding warning");
        removeOnClickFromFields();
        cellArray.forEach(i => {
            let cell = document.getElementById(i);
            cell.onclick =function () {if (!(confirm('All fields for this ship are set.'))) return false}
        })
        console.log("Adding ship to fleet, resetting temporary value")
        fleet.set('ship_' + shipLength, shipArray);
        tempVal = 1;
        shipArray = [];
    } else {
        console.log("Add marked field to temporary array")
        shipArray.push(id);
        console.log("Print array: " + shipArray);
        tempVal++;
        console.log("Increase marked fields value, new value: " + tempVal);
    }
}

// Calculate possible next fields for each chosen field
function calculatePossibleFields(fieldName) {
    console.log("Calculate next possible fields based on previously selected");
    let column = parseInt(fieldName.substring(1,2),10);
    let row = parseInt(fieldName.substring(2),10);
    let possibleFieldsMap = new Map()
    possibleFieldsMap.set('c' + (column-1) + "" + row,'c' + (column-1) + "" + row);
    possibleFieldsMap.set('c' + (column+1) + "" + row,'c' + (column+1) + "" + row);
    possibleFieldsMap.set('c' + column + "" + (row-1),'c' + column + "" + (row-1));
    possibleFieldsMap.set('c' + column + "" + (row+1),'c' + column + "" + (row+1));

    console.log("Print possible fields map: " + possibleFieldsMap)
    cellArray.forEach(i => {
        if(i !== fieldName) {
            let cell = document.getElementById(i);
            if(cell.style.backgroundColor === 'green') {
                console.log("Green field detected : " + i);
            } else {
                if(possibleFieldsMap.has(i)){
                    cell.style.backgroundColor = 'white';
                } else {
                    cell.style.backgroundColor = 'grey';
                }
            }
        }
    })
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



