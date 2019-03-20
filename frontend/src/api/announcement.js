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

export function findAllAnnouncements() {
    return fetch(new Request('/api/free/announcement/list'), init('GET', makeHeaders())).then((response) => response.json());
}
export function saveAnnouncement(announcement) {
    const headers = makeHeaders()
    headers.append('Authorization', getCredentials())
    const body = JSON.stringify(announcement)
    return fetch(new Request('/api/auth/announcement'), init('POST', headers, body)).then((response) => response.ok);
}