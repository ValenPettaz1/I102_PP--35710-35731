module Link ( Link, newL, listCitiesL, linksL, connectsL, capacityL, delayL )
   where

import City ( City )
import Quality ( capacityQ, delayQ, Quality )

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL = Lin

listCitiesL :: Link -> [City]
listCitiesL (Lin city1 city2 _) = [city1, city2]

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city link = city `elem` listCitiesL link

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 link = city1 `elem` listCitiesL link && city2 `elem` listCitiesL link

capacityL :: Link -> Int
capacityL (Lin _ _ qlty) = capacityQ qlty

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin _ _ qlty) = delayQ qlty