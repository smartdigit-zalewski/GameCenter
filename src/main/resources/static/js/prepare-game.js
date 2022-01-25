//GRID COLORS
const SHIP_COLOR = 'LightCoral';
const NOT_ALLOWED_FIELDS = 'LightGray';
const ALLOWED_FIELDS = 'White';

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
    for(let i=0; i < rows; i++) {
        let row = document.createElement("div")
        row.classList.add('row');
        //row.style.border ='0.2px solid black';
        battleground.appendChild(row);
        for(let j=0; j < cols; j++) {
            // Create new div inside battleground and make it correct size
            let col = document.createElement("div");
            col.classList.add('col')
            col.id = 'c' + j + i;
            cellArray.push(col.id);
            col.style.backgroundColor = ALLOWED_FIELDS;
            col.style.height = '60px';
            col.style.border = '0.2px solid black';
            row.appendChild(col);
        }
    }
}

function drawShipButtons(){
    let NUMBER_OF_BUTTONS = 5;
    let container = document.getElementById('buttonsContainer');
    let fragment = document.createDocumentFragment();
    for(let i=1;i <= NUMBER_OF_BUTTONS; i++ ){
        let button = document.createElement("button");
        button.onclick = function () {markShip(i)}
        button.classList.add('btn');
        button.classList.add('btn-danger');
        button.classList.add('btn-lg');
        button.classList.add('mb-1')
        button.id = 'ship_' + i;
        button.innerText = 'Ship ' + i;
        fragment.appendChild(button);
        fragment.appendChild(document.createElement("br"));

    }
    container.appendChild(fragment);
}

// Once player choose Ship to set on grid add onClick to all fields
function markShip(shipLength) {
    markCurrentButton('ship_' + shipLength);
    console.log("Mark ship with length: " + shipLength);
    cellArray.forEach( i => {
            let cell = document.getElementById(i);
            cell.onclick = null;
            cell.style.backgroundColor = ALLOWED_FIELDS;
            cell.onclick = function () {markField(shipLength, i)};
        }
    )
}

// Remove onClick when all ship cells are filled
function removeOnClickFromFields() {
    cellArray.forEach(i => {
        let field = document.getElementById(i);
        field.onclick = null;
    })
}

// Set color for chosen field and add its value to tempArray.
function markField(shipLength, id) {
    let cell = document.getElementById(id);
    if(cell.style.backgroundColor === 'white'){
        console.log("Chosen field: " + cell.id + ", field color change: white -> green");
        cell.style.backgroundColor =  SHIP_COLOR;
        handleClicks(shipLength, id);
        removeFieldFromArray(id);
        calculatePossibleFields(id)


    } else if (cell.style.backgroundColor === NOT_ALLOWED_FIELDS) {
        console.log("Chosen field is not allowed, user can't select it. Add warning")
        confirm('You can\'t select that field')
    } else {
        console.log("Selected previously selected field: NOT IMPLEMENTED YET")
        cell.style.backgroundColor = ALLOWED_FIELDS;
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
        disableCurrentButton('ship_' + shipLength);
        cellArray.forEach(i => {
            let cell = document.getElementById(i);
            cell.onclick =function () {if (!(confirm('All fields for this ship are set.'))) return false}
        })
        console.log("Adding ship to fleet, resetting temporary value")
        fleet.set('ship_' + shipLength, shipArray);
        tempVal = 1;
        shipArray = [];
        if(checkFleetCompletion()) {
            console.log("Fleet completed, block all buttons for ships and show enter game button")
            blockAllButtons();
            showEnterGameButton()
        }
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
            if(cell.style.backgroundColor === SHIP_COLOR) {
                console.log("Green field detected : " + i);
            } else {
                if(possibleFieldsMap.has(i)){
                    cell.style.backgroundColor = ALLOWED_FIELDS;
                } else {
                    cell.style.backgroundColor = NOT_ALLOWED_FIELDS;
                }
            }
        }
    })
}

function checkFleetCompletion() {
    return fleet.size === 5;
}

function disableCurrentButton(buttonName){
    let button = document.getElementById(buttonName);
    button.setAttribute('disabled', 'disabled');
    button.classList.remove('btn-danger');
    button.classList.remove('active')
    button.classList.add('btn-outline-danger');
}

function markCurrentButton(buttonName) {
    let button = document.getElementById(buttonName);
    button.classList.add('active')

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



