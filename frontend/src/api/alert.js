let myHeaders = new Headers();
myHeaders.append('Content-Type', 'application/json');
myHeaders.append('Authorization', 'Basic '+ btoa('admin:admin'))

const myInit = {
    method: 'GET',
    headers: myHeaders,
};

export function findAllAlerts() {
    return fetch(new Request('/api/alert/list'), myInit).then((response) => response.json());
}