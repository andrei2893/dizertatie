module.exports = {
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8090',
                ws: false,
                changeOrigin: true
            },
            '/data': {
                target: 'https://api.openweathermap.org',
                ws: false,
                changeOrigin: true
            },
        }
    }
}