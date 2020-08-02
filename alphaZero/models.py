import peewee
from peewee import *

conn = SqliteDatabase('game.db')
# cursor = conn.cursor()


class BaseModel(Model):
    class Meta:
        database = conn


class Person(BaseModel):
    name = peewee.CharField()
    game_count = peewee.IntegerField(default=0)
    win_count = peewee.IntegerField(default=0)
    rating = peewee.FloatField(default=0)


class Game(BaseModel):
    name = peewee.CharField()


class Net(BaseModel):
    fileName = peewee.CharField()
    game = peewee.ForeignKeyField(Game)
    date = peewee.DateTimeField()
    win_count = peewee.IntegerField(default=0)
    game_count = peewee.IntegerField(default=0)
    rating = peewee.FloatField(default=0)

class Compet(BaseModel):
    user1 = peewee.CharField()
    user2 = peewee.CharField()
    game = peewee.ForeignKeyField(Game)
    game_date = peewee.DateTimeField()
    winner = peewee.IntegerField(default=0)
