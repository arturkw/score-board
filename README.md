# Score Board Library

## General Info

Score Board library provides API for maintaining and displaying Football World Cup Score Board.

## Installation
Compile sources:
```
$mvn clean install  
```

attach artifact in dependencies section, i.e. for Maven.

```
<dependency>
    <version>0.0.1-FINAL</version>
    <groupId>org.ahk</groupId>
    <artifactId>score-board</artifactId>
</dependency>    
```

## Usage
Create new instance:
```
ScoreBoard scoreBoard = new HashMapScoreBoard();
```
Start new game:
```
Game game0 = scoreBoard.startGame("Germany", "England");
```
Finish game:
```
Game finishedGame = scoreBoard.finishGame("Germany", "Poland");
```
Update game:
```
Game updatedGame = scoreBoard.updateScore("A", "B", 1, 0);
```
List of active games:
```
List<Game> games = scoreBoard.gamesByStartDateDesc();
```
