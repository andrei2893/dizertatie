import {getCredentials} from '@/storage'

const makeHeaders = () => {
    let myHeaders = new Headers();
    myHeaders.append('Content-Type', 'application/json');
    return myHeaders
}


const init = (method, headers, body) => ({
    method,
    headers,
    body, 
});

export function findUserByEmail(email, credentials) {
    const headers = makeHeaders()
    headers.append('Authorization', credentials)
    return fetch(new Request('/api/auth/user'), init('POST', headers, email)).then((response) => {
        if (response.ok) {
            return response.json()
        }
        return false;
    });
}


export function register(dto) {
    const headers = makeHeaders()
    const body = JSON.stringify(dto)
    return fetch(new Request('/api/free/user/register'), init('POST', headers, body)).then((response) => response.ok);
}

export function registerClerk(dto, credentials) {
    const headers = makeHeaders()
    headers.append('Authorization', credentials)
    const body = JSON.stringify(dto)
    return fetch(new Request('/api/auth/user/admin'), init('POST', headers, body)).then((response) => response.ok);
}

export function findAllUsers() {
    const headers = makeHeaders()
    headers.append('Authorization', getCredentials())
    return fetch(new Request('/api/auth/user/all'), init('GET', headers)).then((response) => response.json());
}

export function deleteAllUsers(userIds) {
    const headers = makeHeaders()
    headers.append('Authorization', getCredentials())
    const ids = userIds.reduce((acc, val) => acc + ',' + val)
    return fetch(new Request('/api/auth/user/all/delete?ids=' + ids), init('DELETE', headers)).then((response) => response.ok);
}