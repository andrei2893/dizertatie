const credentialsKey='credentialsKey'
const roleKey='roleKey'
const userIdKey='userIdKey'

export function saveCredentials(credentials){
    window.localStorage.setItem(credentialsKey, credentials)
}

export function getCredentials(){
    return window.localStorage.getItem(credentialsKey)
}

export function userIsLoggedIn(){
    return window.localStorage.getItem(credentialsKey) != null
}

export function deleteCredentials(){
    window.localStorage.removeItem(credentialsKey)
}
export function setRole(role){
    window.localStorage.setItem(roleKey, role) 
}
export function getRole(){
    return window.localStorage.getItem(roleKey)
}

export function setUserId(userId){
    window.localStorage.setItem(userIdKey, userId) 
}
export function getUserId(){
    return window.localStorage.getItem(userIdKey)
}