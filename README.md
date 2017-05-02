
## Installation

```
git clone https://github.com/dworznik/calculate-hmac-sha256.git
mvn package
```


## Usage

Use the secret from `Settings / Notifications` in your Shopify admin panel.
```
java -jar target/shopify-hmac-1.0-SNAPSHOT-jar-with-dependencies.jar <secret>
```

The server will listen on port 8080. Configure a webhook with the callback URL set to:
`http://<yourserver>:8080/`

When the servlet receives a webhook request, it will print out information on the received and calculated HMAC.