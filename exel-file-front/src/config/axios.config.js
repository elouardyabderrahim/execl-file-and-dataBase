import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080/api/";
// axios.defaults.headers.common["Authorization"] = AUTH_TOKEN;
axios.defaults.headers.post["Content-Type"] = "application/json";
// axios.defaults.headers["Access-Control-Allow-Origin"] = "*";
// axios.defaults.headers["Access-Control-Allow-Methods"] =
//   "DELETE, POST, GET, OPTIONS";
export const instance = axios.create();
// Now all requests using this instance will wait 2.5 seconds before timing out
instance.defaults.timeout = 5000;

// instance.get("/longReq", {
//   timeout: 60000,
// });

// Interceptors
instance.interceptors.request.use(
  function (config) {
    // Do something before request is sent

    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

axios.interceptors.response.use(
  function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    console.log("interceptor", response);
    return response;
  },
  function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    return Promise.reject(error);
  }
);
