module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import Point ( Point, newP ) 
import City ( City, newC, distanceC )
import Quality ( Quality, newQ, capacityQ, delayQ )

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL = Lin

getCitiesL :: Link -> [City]
getCitiesL (Lin city1 city2 _) = [city1, city2]

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city link = city `elem` getCitiesL link

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 link = city1 `elem` getCitiesL link && city2 `elem` getCitiesL link

capacityL :: Link -> Int
capacityL (Lin _ _ qlty) = capacityQ qlty

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin city1 city2 qlty) = delayQ qlty * distanceC city1 city2

city1 = newC "San Nicolas" (newP 100 250)
city2 = newC "Sarmiento" (newP (-150) 250)
city3 = newC "Comodoro" (newP 100 200)

quality1 = newQ "Baja" 3 0.1
quality2 = newQ "Media" 7 0.05
quality3 = newQ "Alta" 10 0.01

link1_2 = newL city1 city2 quality1
link2_3 = newL city2 city3 quality2

testL = [getCitiesL link1_2 == [city1, city2],
        connectsL city3 link2_3,
        linksL city1 city2 link1_2,
        capacityL link2_3 == 7,
        delayL link1_2 == 250 * 0.1,
        True]
