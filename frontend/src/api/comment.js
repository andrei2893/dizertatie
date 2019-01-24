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

// export function findAllComments() {
//     return fetch(new Request('/api/free/comment/list'), init('GET', makeHeaders()))
//     .then((response) => response.json());
// }

export function findCommentsForAlert(alertId) {
    return fetch(new Request(`/api/free/comment/alert/${alertId}`), init('GET', makeHeaders()))
    .then((response) => response.json());
}

export function submitComment(dto) {
    const headers = makeHeaders()
    headers.append('Authorization', getCredentials())
    return fetch(new Request(`/api/auth/comment`), init('POST', headers,JSON.stringify(dto)))
    .then((response) => response.json());
}