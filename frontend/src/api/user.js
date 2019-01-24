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