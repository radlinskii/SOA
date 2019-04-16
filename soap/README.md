# SOAP module 


### prerequisites

- installed `java8`
- installed and configured `wildfly 14+`

### development

Getting started:

1. clone this repo
2. install maven dependencies
3. in a separate terminal tab run wildfly's `standalone.sh` script

If you make changes in `soap-ejb/src/main/java`
- run `mvn clean package wildfly:deploy` command to deploy it fo `wildfly`'s server

Now you should see changes done in `wsdl` and be able test them (e.g. in [SoapUI](https://www.soapui.org/))

If you want to test changes in the `connector` module:
1. run `wsimport -Xnocompile -p  client -s soap-connector/src/main/java http://localhost:8080/soap-ejb/StudentController\?wsdl` to *re*-generate the client's classes
2. run the main method in `soap-connector/src/main/java/main.Main` class to see the responses

> Note: you can edit `soap-connector/src/main/java/client` classes to e.g. add them `toString` to method