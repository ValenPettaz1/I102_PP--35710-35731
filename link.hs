module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import Point ( Point, newP ) 
import City ( City, newC, distanceC )
import Quality ( Quality, newQ, capacityQ, delayQ )

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 qlty | city1 /= city2 = Lin city1 city2 qlty
                      | city1 == city2 = error "Las ciudades deben ser distintas"
                      | otherwise = error "Link inválido"

getCitiesL :: Link -> [City]
getCitiesL (Lin city1 city2 _) = [city1, city2]

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city link = city `elem` getCitiesL link

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 link | city1 /= city2 = city1 `elem` getCitiesL link && city2 `elem` getCitiesL link
                        | city1 == city2 = error "Las ciudades deben ser distintas"
                        | otherwise = error "Error de entrada"

capacityL :: Link -> Int
capacityL (Lin _ _ qlty) = capacityQ qlty

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin city1 city2 qlty) = delayQ qlty * distanceC city1 city2