import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:9192"
})

api.interceptors.response.use(function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response.data ? response.data : { statusCode: response.status };
}, function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    if (error.response) {
        console.log(error.response);
    } else if (error.request) {
        console.log(error.request)
    } else {
        console.log("Unexpected error ", error.message);
    }
    return error.response;
});

export default api