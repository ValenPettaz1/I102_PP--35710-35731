module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR )
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
foundR (Reg cities links tunels) city 
   | city `elem` cities = error "La ciudad ya está en la región"
   | equalLocation = error "La posición indicada corresponde a otra ciudad ya existente en la región"
   | city `notElem` cities = Reg (city:cities) links tunels
   where
      equalLocation = foldr (||) False [distanceC city cityR == 0 | cityR <- getCitiesR (Reg cities links tunels)]
   

getCitiesR :: Region -> [City]
getCitiesR (Reg cities _ _) = cities

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 qlty 
   | city1 == city2 = error "Las ciudades deben ser distintas"
   | city1 `notElem` cities = error "La ciudad 1 no está en la región"
   | city2 `notElem` cities = error "La ciudad 2 no está en la región"
   | linkedR (Reg cities links tunels) city1 city2 = error "Las ciudades ya están enlazadas"
   | otherwise = Reg cities (newL city1 city2 qlty:links) tunels                                                               

getLinksR :: Region -> [Link]
getLinksR (Reg _ links _) = links

countTarget :: Eq a => a -> [a] -> Int
countTarget target [] = 0
countTarget target (x:xs) = if target == x then 1 + countTarget target xs else countTarget target xs

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) targetCities 
   | (length targetCities - 1) > length possibleLinks  = error "Conflicto de links insuficientes"
   | targetCities == [] = error "No hay ciudades para enlazar"
   | fullLink (Reg cities links tunels) targetCities = error "No hay capacidad disponible"
   | otherwise = Reg cities links (newT possibleLinks:tunels) 
   where 
      possibleLinks = [link | link <- getLinksR (Reg cities links tunels), countTarget True [connectsL city link | city <- targetCities] >= 2] 

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunels) city1 city2 = foldr (||) False [connectsT city1 city2 tunel | tunel <- tunels]

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = foldr (||) False [linksL city1 city2 link | link <- links]

getTunelsR :: Region -> [Tunel]
getTunelsR (Reg _ _ tunels) = tunels

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg cities links tunels) city1 city2 
   | connectedR (Reg cities links tunels) city1 city2 = delayT targetTunel 
   | otherwise = error "Las ciudades no están conectadas"
   where
      targetTunel = head [tunel | tunel <- getTunelsR (Reg cities links tunels), connectsT city1 city2 tunel]

countTunels :: Region -> Link -> Int
countTunels (Reg _ _ tunels) targetLink = countTarget True [usesT targetLink tunel| tunel <- tunels]

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR region city1 city2 = capacityL targetLink - countTunels region targetLink where
   targetLink = head [link | link <- getLinksR region, linksL city1 city2 link] 

fullLink :: Region -> [City] -> Bool
fullLink region (city:cities) 
   | length (city:cities) == 1 = False
   | otherwise = availableCapacityForR region city (head cities) <= 0 || fullLink region cities