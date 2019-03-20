const appid = '17d14ee530e2f26aa647cbd09ee9edba';

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


export function getWeather(lat, lng) {
    const headers = makeHeaders()

    const url = `/data/2.5/weather?lat=${lat}&lon=${lng}&appid=${appid}`
    return fetch(new Request(url), init('GET', headers)).then((response) => response.json());
}