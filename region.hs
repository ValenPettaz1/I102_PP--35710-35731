module Region ( Region, newR, foundR, linkR, tunelR connectedR, linkedR, delayR, availableCapacityForR)
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

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 qlty = Reg cities (newL city1 city2 qlty:links) tunels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) city1 city2 qlty = Reg cities links (newT):tunels

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg cities links tunels) city1 city2 = 

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades

city1t = newC "San Nicolas" (newP 100 250)
city2t = newC "Sarmiento" (newP (-150) 250)
city3t = newC "Comodoro" (newP 100 200)
city4t = newC "La Pampa" (newP 100 0)
city5t = newC "Antartida" (newP 0 (-1000))
city6t = newC "Jujuy" (newP 1000 1000)

quality1t = newQ "Baja" 3 0.1
quality2t = newQ "Media" 7 0.05
quality3t = newQ "Alta" 10 0.01

link1_2t = newL city1t city2t quality1t
link2_3t = newL city2t city3t quality2t
link3_4t = newL city3t city4t quality1t
link4_5t = newL city4t city5t quality3t
link5_6t = newL city5t city6t quality2t

tunel1_4t = newT [link1_2t, link2_3t, link3_4t]
tunel1_6t = newT [link1_2t, link2_3t, link3_4t, link4_5t, link5_6t]

region1 = newR `foundR` city1t `foundR` city2t `foundR` city3t `foundR` city4t 