# DiceOnAYacht
 This game is played with five eight-sided dice. The implementation should include a function that, given an array of the five dice values and a category indicator, will return the score that you would get for that category. It should also include a function that could iterate through all the categories and find the one with the highest score.

Here is how to score each of the categories:
Ones, Twos, Threes, Fours, Fives, Sixes, Sevens, Eights: Sum of all dice that match the title of the category.  For example {4,4,4,4,5} scores 16 for fours.
ThreeOfAKind: Sum of all dice if there are at least three dice that are the same, otherwise zero. For example {1,1,1,2,8} scores 13.
FourOfAKind: Sum of all dice if there are at least four dice that are the same, otherwise zero. For example {1,1,1,1,8} scores 12.
FullHouse: If there are three of one kind and two of another score 25, otherwise score zero. For example {1,1,1,8,8} scores 25.
SmallStraight: If there are four dice in sequence score 30, otherwise zero. For example {1,2,3,4,7} scores 30.
LargeStraight: If all five dice fall in sequence score 40, otherwise zero.  For example {3,4,5,6,7} scores 40.
AllDifferent: If all five dice have unique values score 40, otherwise zero.  For example {1,2,4,6,8} scores 40.
Chance: Sum of all dice. For example {1,2,1,8,8} scores 20.
AllSame: If all five dice have the same value score 50, otherwise zero.  For example {1,1,1,1,1} scores 50.

