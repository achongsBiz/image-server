<template>
    <div>
        <p>Select an image from your file system and click upload.</p>
        <input type="file" v-on:change="updateObject" ref="file">
        <button v-bind:disabled="imageObject == null" v-on:click="handleUpload">Upload!</button>
        <br><br><br>
        <section id="output">

            <!--Images are sized down with CSS-->
            <div id="gallery">
                <img v-for="image in images" v-bind:key="image.id" v-bind:src="image.name"/>
            </div>

            <!--Direct URL to images displayed-->
            <div id="links">
                <p>Actual image links:</p>
                <div v-for="image in images" v-bind:key="image.id">
                    <a v-bind:href="image.name">{{ image.name }}</a>
                    <br><br>
                </div>
            </div>

        </section>


    </div>
</template>

<script>

    import ImageService from '../services/ImageService.js';
    export default {
        data() {
            return {
                imageObject : null,
                images: []
            }

        },
        
        methods: {
            updateObject() {
                this.imageObject = this.$refs.file.files[0];

            }, 


            handleUpload() {
                console.log(this.imageObject);
                ImageService.upload(this.imageObject).then(
                    (resp) => {
                        if (resp.status === 201) {
                            window.alert("image uploaded");
                            this.handleRetrieval();
                        }
                    }
                );

            }, 

            handleRetrieval() {
                ImageService.retrieveAll().then(
                    (resp) => {
                        this.images = resp.data;
                    }
                );

            }
        }, 

        created() {
            this.handleRetrieval();
        }
    }

</script>

<style>

    #output {
        display: flex;
    }

    #gallery {
        display: flex;
        border: 1px dashed gray;
        flex-wrap: wrap;
        padding: 10px;
        width: 50%;
        margin: 5px;
    }

    #gallery > img {
        width: 200px;
        margin: 20px;
    }

    #links {
        border: 1px dashed gray;
        width: 50%;
        margin: 5px;
    }

    #links > a {
        display: inline-block;
    }
</style>