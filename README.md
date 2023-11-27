# Image Server

## Introduction

This is a reference project for how to setup an image server using SpringBoot and a PostgreSQL database. 
The server is capable of handling image uploads and retrieve images independently or in batch.

Included in this project is a simple front end client
written in VUE.js is provided to shows examples for how a client could communicate with the server.

## Setup

You should have a PostgreSQL database installed or available.

1. On the server, setup your database and server domain configuration within application.properties.

2. From within your database management system make a database with the name you picked in step 1 then run the contents of Build.sql.  

3. On the client, setup the server's address within ImageServices.js.

4. Run the server.

5. Run the client through npm.

## Uploading Images

Upload is achieved through a post endpoint with a multipart file body:

``` (POST) /post ```

A success will result in a 201 status, along with the following object: 

```
{
    name: 'domain/file1.png'
}
```

The client project provides an example of how to create a HTML form capable of submitting such a request. Upon successful upload, the data is stored within a BYTEA column in Postgres.


## Individual Image Retrieval

Any uploaded image can be retrieved by name using a GET request:

``` (GET) /image/{file-name} ```

The URL for the GET request can be directly inserted into a HTML document for use in items like img tags. The image URL's can also be used in a standalone fashion by directly being pasted onto a browser.


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

## Project Limitations

This project is meant as a reference only, and not a complete implementation. You will need to figure out some things on your own. A few of these come to mind:

1. Image compression to better control file sizes

2. Image removal

3. Handling duplicate image names

4. Integrating the features into an account structure

