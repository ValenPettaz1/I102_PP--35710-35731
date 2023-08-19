module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import Point ( Point, newP)
import City ( City, newC, distanceC )
import Quality

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

city1t = newC "San Nicolas" (newP 100 250)
city2t = newC "Sarmiento" (newP (-150) 250)
city3t = newC "Comodoro" (newP 100 200)

quality1t = newQ "Baja" 3 0.1
quality2t = newQ "Media" 7 0.05
quality3t = newQ "Alta" 10 0.01

link1_2t = newL city1t city2t quality1t
link2_3t = newL city2t city3t quality2t

testL = [getCitiesL link1_2t == [city1t, city2t],
        connectsL city3t link2_3t,
        linksL city1t city2t link1_2t,
        capacityL link2_3t == 7,
        delayL link1_2t == 250 * 0.1,
        True]
