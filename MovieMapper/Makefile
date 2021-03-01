Test: Compile
	@echo
	@echo "Frontend Tests:"
	java TestFrontend
	@echo
	@echo "Backend Tests:"
	java TestBackend
	@echo
	@echo "Data Reader Tests:"
	java TestMovieDataReader
	
Run: Compile
	java Frontend

Compile: Frontend.class Backend.class Movie.class MovieDataReader.class MovieDataReaderInterface.class HashTableMap.class MapADT.class MovieInterface.class BackendInterface.class TestFrontend.class TestBackend.class TestMovieDataReader.class


Frontend.class: Frontend.java
	javac Frontend.java

Backend.class: Backend.java
	javac Backend.java

Movie.class: Movie.java
	javac Main.java

MovieDataReader.class: MovieDataReader.java
	javac MovieDataReader.java

MovieDataReaderInterface.class: MovieDataReaderInterface.java
	javac MovieDataReader.java

HashTableMap.class: HashTableMap.java
	javac HashTableMap.java

MapADT.class: MapADT.java
	javac MapADT.java

MovieInterface.class: MovieInterface.java
	javac MovieInterface.java

BackendInterface.class: BackendInterface.java
	javac BackendInterface.java
	
TestFrontend.class: TestFrontend.java
	javac TestFrontend.java

TestBackend.class: TestBackend.java
	javac TestBackend.java

TestMovieDataReader.class: TestMovieDataReader.java
	javac TestMovieDataReader.java


