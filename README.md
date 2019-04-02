# SOAP module 


### prerequisites

- installed `java8`
- installed and configured `wildfly 14+`

### getting started

1. clone this repo
2. install maven dependencies
3. in a separate terminal tab run wildfly's `standalone.sh` script
4. run `mvn clean package wildfly:deploy` command
5. run `wsimport -Xnocompile -p  client -s soap-connector/src/main/java http://localhost:8080/soap-ejb/StudentController\?wsdl` to *re*-generate the client's classes
6. run `soap-connector/src/main/java/main.Main` class to see the responses

