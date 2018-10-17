# ElasticSearch

Prerequisites to run the project

1. Download Elasticsearch https://www.elastic.co/downloads/elasticsearch 
2. Download Kibana  https://www.elastic.co/downloads/kibana 
3. Extract both Elastic search and Kibana
4. Goto Elasticsearch /bin folder and run elasticsearch executable
5. Goto Kibana /bin folder and run kibana executive

What does the source code do?
1. Dump data into elastic search
2. When a document of a type under an index is update its audit i.e. previous version is stored in audit index
Example: 
   Index: data_box 
   type: box
   Index(audit): data_box_audit
3. Application provides a rest implementation to POST json data to application and Elastic restclient is used to dump the provided data
4. Use Kibana console to view all the created indexes

Future implemention:
1. Provide search of documnets
2. Provide audit search of documents

