# Image Server

## Introduction

This is a reference project for how to setup an image server using SpringBoot and a PostgreSQL database. 
The server is capable of handling image uploads and retrieve images independently or in batch.

Also included in this project is an example client written in VUE.js that consumes the server's endpoints.

## Setup

You should have a PostgreSQL database installed or available.

1. On the server, setup your database and server domain configuration within application.properties.

2. From within your database management system make a database with the name you picked in step 1 then run the contents of Build.sql.  

3. On the client, setup the server's address within ImageServices.js.

4. Run the server.

5. Run the client through npm.

## Uploading Images

![screenshot](https://raw.githubusercontent.com/achongsBiz/readme-files/master/image-storage/api.png)

Upload is achieved through a post endpoint with a multipart file body:

``` (POST) /post ```

A success will result in a 201 status, along with the following object: 

```
{
    name: 'domain/file1.png'
}
```

The client project provides an example of how to create a HTML form capable of submitting such a request. Upon successful upload, the image is stored within a BYTEA column in Postgres.

If you wish to test the endpoint on Postman, send a request to /post. Under ```Body``` select ```form-data```. Enter image under the ```Key``` column and select ```File```. Under Value, select an image from your machine. (See the screenshot above)


## Individual Image Retrieval

Any uploaded image can be retrieved by name using a GET request:

``` (GET) /image/{file-name} ```

The URL for the GET request can be directly inserted into a HTML document for use in items like img tags.


## Bulk Image Retrieval

A listing of all stored images can be retrieved through:

``` (GET) /all-images ```

The request returns a JSON array in the following format:

```
[
    {
        name: 'domain/file1.png'
    },

    {
        name: 'domain/file2.png'
    }
]
```

## Client

![screenshot](https://raw.githubusercontent.com/achongsBiz/readme-files/master/image-storage/api-2.png)

A client written in VUE is available on this project. It shows how to build a form for uploading images and how to retrieve them.

## Project Limitations

This project is meant as a reference only, and not a complete implementation. You will need to figure out some things on your own. A few of these come to mind:

1. Image compression to better control file sizes

2. Image removal

3. Handling duplicate image names

4. Integrating the features into an account structure

