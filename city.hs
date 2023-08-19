module City ( City, newC, nameC, distanceC )
   where

import Point  

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC = Cit 

nameC :: City -> String
nameC (Cit name _) = name

distanceC :: City -> City -> Float
distanceC (Cit _ loc1) (Cit _ loc2) = difP loc1 loc2

city1 = newC "San Nicolas" (newP 100 250)
city2 = newC "Sarmiento" (newP (-150) 250)
city3 = newC "Buenos Aires" (newP 0 0)
city4 = newC "La Pampa" (newP 100 0)
city5 = newC "Antartida" (newP 0 (-1000))
city6 = newC "Jujuy" (newP 1000 1000)

   
testC = [distanceC city1 city2 == 250.0,
        distanceC city2 city1 == 250.0,
        nameC city3 == "Buenos Aires",
        True]

