import 'whatwg-fetch'

export const register = (nickname) -> {
    fetch('/register/'+nickname, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: ''
    }).then(function(response) {
        return response.json()
    }).then(function(json) {
        console.log('parsed json', json)
        
        //TODO dispatch SET_PLAYER_ID 
    }).catch(function(ex) {
        console.log('parsing failed', ex)
    })
}

export const check = (playerId) -> {
    fetch('/check/'+playerId)
        .then(function(response) {
            return response.json()
        }).then(function(json) {
        console.log('parsed json', json)
        
        //TODO if state changed dispatch win/loose
    }).catch(function(ex) {
        console.log('parsing failed', ex)
    })
}

export const ready = (playerId) -> {
    fetch('/ready/'+playerId)
        .then(function(response) {
            return response.json()
        }).then(function(json) {
        console.log('parsed json', json)
        
        //TODO if opponent is ready dispatch opponentActive
    }).catch(function(ex) {
        console.log('parsing failed', ex)
    })
}

export const move = (playerId, move) -> {
    fetch('/move/'+playerId+"/"+move)
        .then(function(response) {
            return response.json()
        }).then(function(json) {
        console.log('parsed json', json)

        //TODO if state changed dispatch win/loose
    }).catch(function(ex) {
        console.log('parsing failed', ex)
    })
}


