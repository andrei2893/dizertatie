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

export function findAllAlerts() {
    return fetch(new Request('/api/free/alert/list'), init('GET', makeHeaders())).then((response) => response.json());
}
export function saveAlert(alert) {
    const headers = makeHeaders()
    headers.append('Authorization', getCredentials())
    const body = JSON.stringify(alert)
    return fetch(new Request('/api/auth/alert'), init('POST', headers, body)).then((response) => response.ok);
}