import axios from 'axios';

const http = axios.create(
    {
        baseURL: "http://localhost:8080"
    }
);

export default {

    upload(imageObj) {

        let formData = new FormData();
        formData.append('image', imageObj);

        return http.post("/post", formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
              }

         });
    },

    retrieveAll() {
        
        return http.get("/all-images");

    }

}