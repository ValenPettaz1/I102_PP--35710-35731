module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR )
   where

import Point
import City
import Link
import Quality
import Tunel
import Control.Exception ( SomeException, evaluate, tryJust ) --Estos dos últimos Hackages se usan en el manejo de excepciones
import System.IO.Unsafe ( unsafePerformIO )

data Region = Reg [City] [Link] [Tunel] deriving (Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región 
foundR (Reg cities links tunels) city | city `notElem` cities = Reg (city:cities) links tunels
                                      | city `elem` cities = error "La ciudad ya está en la región"
                                      | otherwise = error "Acción de agregar ciudad inválida"

addCitiesR :: Region -> [City] -> Region
addCitiesR region [] = region
addCitiesR region cities = foldl foundR region cities 

getCitiesR :: Region -> [City]
getCitiesR (Reg cities _ _) = cities

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) city1 city2 qlty | city1 == city2 = error "Las ciudades deben ser distintas"
                                                 | distanceC city1 city2 == 0 = error "Las ciudades deben estar a una distancia mayor a 0"
                                                 | city1 `notElem` cities = error "La ciudad 1 no está en la región"
                                                 | city2 `notElem` cities = error "La ciudad 2 no está en la región"
                                                 | linksL city1 city2 (head links) = error "Ya existe un link entre estas ciudades" 
                                                 | otherwise = Reg cities (newL city1 city2 qlty:links) tunels                  

getLinksR :: Region -> [Link]
getLinksR (Reg _ links _) = links

countTarget :: Eq a => a -> [a] -> Int
countTarget target [] = 0
countTarget target (x:xs) = if target == x then 1 + countTarget target xs else countTarget target xs

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) targetCities | length targetCities - 1 >= length links  = error "No hay links suficientes"
                                              | otherwise = Reg cities links (newT possibleLinks:tunels) 
                                              where --OJO: se considera solo el caso más fácil, falta pulir mucho.
                                                 possibleLinks = [link | link <- getLinksR (Reg cities links tunels), countTarget True [connectsL city link | city <- targetCities] >= 2]


connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunels) city1 city2 = foldr (||) False [connectsT city1 city2 tunel | tunel <- tunels]

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = foldr (||) False [linksL city1 city2 link | link <- links]

getTunelsR :: Region -> [Tunel]
getTunelsR (Reg _ _ tunels) = tunels

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg cities links tunels) city1 city2 | connectedR (Reg cities links tunels) city1 city2 = delayT targetTunel where
   targetTunel = head [tunel | tunel <- getTunelsR (Reg cities links tunels), connectsT city1 city2 tunel]
--OJO que pasa si las ciudades No estan conectadas

countTunels :: Region -> Link -> Int
countTunels (Reg _ _ tunels) targetLink = countTarget True [usesT targetLink tunel| tunel <- tunels]

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR region city1 city2 = capacityL targetLink - countTunels region targetLink where
   targetLink = head [link | link <- getLinksR region, linksL city1 city2 link]



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

tunel2_5 = newT [link2_3, link3_4, link4_5]
tunel1_4 = newT [link1_2, link2_3, link3_4]
tunel1_6 = newT [link1_2, link2_3, link3_4, link4_5, link5_6]



--OJO ver un modo más eficiente de crear una región. Capaz usando map.
regionVoid = newR

regionCities1_6 = addCitiesR regionVoid [city1, city2, city3, city4, city5, city6]

regionLink1_2 = linkR regionCities1_6 city1 city2 quality1
regionLink2_3 = linkR regionLink1_2 city2 city3 quality2
regionLink3_4 = linkR regionLink2_3 city3 city4 quality1

regionTunel1_4 = tunelR regionLink3_4 [city1, city2, city3, city4, city5, city6]
regionTunel1_4y1_2 = tunelR regionTunel1_4 [city1, city2]
regionTunel1_4y1_2x2 = tunelR regionTunel1_4y1_2 [city1, city2]
regionTunel1_4y1_2x3 = tunelR regionTunel1_4y1_2x2 [city1, city2] --OJO: capacidades negativas



--Control de excepciones (ejemplo para entender)
testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()

result :: Int -> Int
result x | x > 5 = 4
         | otherwise = error "hey"

-- ahora pueden evaluar (Tira verdadero si salta un error, falso de lo contrario)
t = [ testF (result 3 ),
      testF (result 8 ) ]

--OJO ARCHIVO APARTE DE TODAS LAS PRUEBAS