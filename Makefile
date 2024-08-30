# Really simple wrapper on Maven's command to build, test, & run Farkle
# Maven is a package builder and runner tool for Java
#  https://maven.apache.org/

build:
	@echo "Building Farkle package - results in target/ directory"
	mvn package -DskipTests

test:
	@echo "Running all tests"
	mvn test

mvn-run:
	@echo "Running Farkle main - see pom.xml for arguments passed"
	mvn exec:java

run: build
	@echo "Running Farkle main without maven overhead"
	java -jar target/farkle-*.jar

fast-run:
	java -jar target/farkle-*.jar

spellcheck:
	-codespell src/

setup-dependencies:
	apt update
	apt install -y maven python3-pip checkstyle
	pip3 install codespell

javadoc:
	@echo "Creating javadoc materials"
	@echo "These go into: target/site/apidocs/"
	@echo "Load up index.html to read them"
	-mvn javadoc:javadoc

clean:
	@echo "Removing temporary build files and directories"
	rm -rf target

lint:
	@echo "Running spotless linter to check source files"
	mvn spotless:check

lint-autofix:
	@echo "Autofixing linting errors"
	mvn spotless:apply

build-docker:
	@echo "Building the course official CI docker image"
	docker build -t cpsc224farklerunner:latest .

push-docker:
#	@echo "Pushing updated farkle docker image to local registry"
#	@echo "See: https://www.docker.com/blog/how-to-use-your-own-registry-2/"
#	docker tag cpsc224farkle:latest localhost:5000/cpsc224farkle:latest
#	docker push localhost:5000/cpsc224farkle:latest
#	Note: need to run this once in the shell for credentials:
#	docker login
	@echo "Pushing image to DockerHub"
	docker tag cpsc224farklerunner:latest acrandal/cpsc224farklerunner:latest
	docker push acrandal/cpsc224farklerunner:latest
