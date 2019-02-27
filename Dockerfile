FROM openjdk:8-jdk

WORKDIR /home/java

# Install dependencies
RUN apt-get update && apt-get install -y \
    build-essential \
    mysql-client \
    redis-server \
    libpng-dev \
    libjpeg62-turbo-dev \
    libfreetype6-dev \
    locales \
    zip \
    jpegoptim optipng pngquant gifsicle \
    vim \
    unzip \
    git \
    curl

# Download and install Gradle
RUN \
    cd /usr/local && \
    curl -L https://services.gradle.org/distributions/gradle-5.2.1-bin.zip -o gradle-5.2.1-bin.zip && \
    unzip gradle-5.2.1-bin.zip && \
    rm gradle-5.2.1-bin.zip
ENV PATH ${PATH}:/usr/local/gradle-5.2.1/bin

COPY . /home/java

RUN gradle wrapper
RUN ./gradlew build

EXPOSE 3001
CMD java -jar build/libs/java.jar
