let myHeaders = new Headers();
myHeaders.append('Content-Type', 'application/json');

const myInit = {
    method: 'GET',
    headers: myHeaders,
};

export function findAllAlerts() {
    return fetch(new Request('/api/alert/list'), myInit).then((response) => response.json());
}