module City ( City, newC, nameC, distanceC )
   where

import Point ( Point, difP )

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC = Cit 

nameC :: City -> String
nameC (Cit name _) = name

distanceC :: City -> City -> Float
distanceC (Cit _ loc1) (Cit _ loc2) = difP loc1 loc2

