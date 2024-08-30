FROM myoung34/github-runner:latest
WORKDIR /app
COPY . .
RUN apt update 
RUN apt install -y make 
RUN apt install -y openjdk-11-jdk
RUN make setup-dependencies 
RUN make build && make test && make lint && make spellcheck
RUN ls -A1 
RUN ls -A1 | xargs rm -rf

# Set workdir to the one defaulted to by myoung34: /actions-runner
WORKDIR /actions-runner

RUN echo "Build complete"
