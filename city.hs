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

sannicolas = newC "San Nicolas" (newP 100 250)
sarmiento = newC "Sarmiento" (newP (-150) 250)

test = [distanceC sarmiento sannicolas == 250.0,
        distanceC sannicolas sarmiento == 250.0,
        nameC sarmiento == "Sarmiento",
        True]

