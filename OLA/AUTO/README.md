  # JBoss EAP 6.4 Enterprise App

This app is a REST API service, ready-to-use out of the box for JBoss EAP 6.4. You can use Maven to build the entire app (`mvn clean install`) and deploy to JBoss. _Recommended:_ use your favourite IDE to hot deploy straight to the app server instead.

- JBoss EAP 6.x+
- Maven 3.x
- Your favouite IDE

You will need to run the full standalone profile to take advantage of some example message-queues bundled within JBoss EAP:

```
$ $JBOSS_HOME/standalone.sh -c standalone-full.xml
```

You will need to install a new security domain to your server via `jboss-cli.sh`:

```
/subsystem=security/security-domain=sample:add

/subsystem=security/security-domain=sample/authentication=classic:add

/subsystem=security/security-domain=sample/authentication=classic/login-module=UsersRoles:add(code=org.jboss.security.auth.spi.UsersRolesLoginModule,flag=required,module-options=[("userProperties"=>"users.properties"),("rolesProperties"=>"roles.properties")])
```
---


One is an unprotected resource …

```
curl -XGET http://localhost:8080/api/helloworld
```

… while the other one requires authentication

```
curl -XGET http://localhost:8080/api/protected/helloworld -H “Authorization: Basic $(echo -n testUser:password | base64)”
```


There are also two simple POST and GET calls to save and fetch a list of user records¡ stored in an in-memory H2 database:

- Add a new user:

```
curl -XPOST -i http://localhost:8080/api/user -H "Content-Type: application/json" -d "{\"username\":\"testUser\",\"firstName\":\"Test\",\"lastName\":\"User\"}"
```

- Fetch the list of users:

```
curl -XGET -i http://localhost:8080/api/user
```


Calling the following endpoint would cause `AsyncRS.java` to push several process _messages_ onto the `ExampleQueue` queue for further processing.

```
curl -XPOST -i http://localhost:8080/api/async/start
```

These are subsequently fetched and processed by `SampleSLSB.java`, without the need to block `AsyncRS.java`.

---


The project is essentially split into three:

- **ejb** - contains your business logic services and Java EE components (stateless session beans, message driven beans, persistence and so on)
- **api** - JAX-RS-enabled web application which exposes some endpoints
- **ear** - the package which collect and pack the above two modules into a deployable _.ear_ file.