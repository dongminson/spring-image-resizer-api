In the project directory, you can run:

```sh
mvn install
mvn spring-boot:run
```

The API will be accessible at [http://localhost:8080](http://localhost:8080)

You can test it with:

```bash
curl -X POST -F "file=@a.jpg" -F "width=50" -F "height=50" http://localhost:8080/resize > resized_image.jpg
```