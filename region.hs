module Region ( Region, newR, foundR, linkR, tunelR, connectedR) --, linkedR, delayR, availableCapacityForR )
   where

import Point
import City
import Link
import Quality
import Tunel

data Region = Reg [City] [Link] [Tunel] deriving (Show)

newR :: Region
newR = Reg [] [] [] 

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región 
foundR (Reg cities links tunels) city = Reg (city:cities) links tunels

getCitiesR :: Region -> [City]
getCitiesR (Reg cities links tunels) = cities

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 qlty = Reg cities (newL city1 city2 qlty:links) tunels

getLinksR :: Region -> [Link]
getLinksR (Reg cities links tunels) = links

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) targetCities = Reg cities links (newT possibleLinks:tunels) where --OJO: se considera solo el caso más fácil, falta pulir mucho.
   possibleLinks = [link | link <- getLinksR (Reg cities links tunels), foldr (||) False [connectsL city link | city <- targetCities]] 

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunels) city1 city2 = foldr (||) False [connectsT city1 city2 tunel | tunel <- tunels]

{-
linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades-}

city1 = newC "San Nicolas" (newP 100 250)
city2 = newC "Sarmiento" (newP (-150) 250)
city3 = newC "Comodoro" (newP 100 200)
city4 = newC "La Pampa" (newP 100 0)
city5 = newC "Antartida" (newP 0 (-1000))
city6 = newC "Jujuy" (newP 1000 1000)

quality1 = newQ "Baja" 3 0.1
quality2 = newQ "Media" 7 0.05
quality3 = newQ "Alta" 10 0.01

link1_2 = newL city1 city2 quality1
link2_3 = newL city2 city3 quality2
link3_4 = newL city3 city4 quality1
link4_5 = newL city4 city5 quality3
link5_6 = newL city5 city6 quality2

tunel1_4 = newT [link1_2, link2_3, link3_4]
tunel1_6 = newT [link1_2, link2_3, link3_4, link4_5, link5_6]

--OJO ver un modo más eficiente de crear una región. Capaz usando map.
--regionCities = linkR (newR `foundR` city1 `foundR` city2 `foundR` city3 `foundR` city4) city1 city2 quality1  
regionCities = newR `foundR` city1 `foundR` city2 `foundR` city3 `foundR` city4
regionLink1_2 = linkR regionCities city1 city2 quality1 
regionLink2_3 = linkR regionLink1_2 city2 city3 quality2
regionLink3_4 = linkR regionLink2_3 city3 city4 quality1
regionTunel1_4 = tunelR regionLink3_4 [city1, city2, city3, city4, city5, city6]


