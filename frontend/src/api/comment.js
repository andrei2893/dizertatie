let myHeaders = new Headers();
myHeaders.append('Content-Type', 'application/json');

const myInit = {
    method: 'GET',
    headers: myHeaders,
};

export function findAllComments() {
    return fetch(new Request('/api/comment/list'), myInit).then((response) => response.json());
}