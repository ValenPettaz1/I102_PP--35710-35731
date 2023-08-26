module TestsZone where

import Point
import Link
import City
import Quality
import Tunel
import Region
import Control.Exception ( SomeException, evaluate, tryJust )
import System.IO.Unsafe ( unsafePerformIO )

--Control de excepciones 
testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()

addCitiesR :: Region -> [City] -> Region
addCitiesR region [] = region
addCitiesR region cities = foldl foundR region cities

addLinksR :: Region -> [(City, City)] -> [Quality] -> Region
addLinksR region [] [] = region
addLinksR region (par:cities) (qlty:qlties) = addLinksR (linkR region (fst par) (snd par) qlty) cities qlties

addTunelsR :: Region -> [[City]] -> Region
addTunelsR = foldl tunelR


--Pruebas de puntos:
point1 = newP 100 250
point2 = newP (-150) 250

testP = [difP point1 point2 == 250.0,
        difP point2 point1 == 250.0,
        True]


--Pruebas de ciudades:
city1 = newC "San Nicolas" (newP 100 250)
city2 = newC "Sarmiento" (newP (-150) 250)
city3 = newC "Buenos Aires" (newP 0 0)
city4 = newC "La Pampa" (newP 100 0)
city5 = newC "Antartida" (newP 0 (-1000))
city6 = newC "Jujuy" (newP 1000 1000)
city6' = newC "Fake Jujuy" (newP 1000 1000)
city7 = newC "Atlantis" (newP 100 1000)


testC = [distanceC city1 city2 == 250.0,
        distanceC city2 city1 == 250.0,
        nameC city3 == "Buenos Aires",
        True]


--Pruebas de calidades:
quality0 = newQ "Muy baja" 1 0.5
quality1 = newQ "Baja" 3 0.1
quality2 = newQ "Media" 7 0.05
quality3 = newQ "Alta" 10 0.01

testQ = [capacityQ quality1 == 3,
        delayQ quality1 == 0.1,
        True]

negativeCapacityQ = newQ "Baja" (-3) 0.1 -- Capacidad no positiva
emptyNameQ = newQ "" 3 0.1 -- Sin nombre
negativeDelay = newQ "Media" 3 (-0.1) --Delay no positivo

testErrorsQ = map testF [negativeCapacityQ,
                        emptyNameQ,
                        negativeDelay]


--Pruebas de links:
link1_2 = newL city1 city2 quality1
link2_3 = newL city2 city3 quality2
link3_4 = newL city3 city4 quality1
link4_5 = newL city4 city5 quality3
link5_6 = newL city5 city6 quality0

testL = [connectsL city3 link2_3,
        linksL city1 city2 link1_2,
        capacityL link2_3 == 7,
        delayL link1_2 == 250 * 0.1,
        True]

sameCityL = newL city1 city1 quality1 --Linkear una misma ciudad.
checkSameCityL = linksL city1 city1 link1_2 --Chequear si está conectada la ciudad consigo misma.

testErrorsL = [testF sameCityL,
              testF checkSameCityL,
              True]

--Prueba de tunel:
tunel1_4 = newT [link1_2, link2_3, link3_4]
tunel2_5 = newT [link2_3, link3_4, link4_5]
tunel1_6 = newT [link1_2, link2_3, link3_4, link4_5, link5_6]

testConnectsT = [not (connectsT city1 city2 tunel1_4),
                not (connectsT city4 city3 tunel1_4),
                not (connectsT city2 city4 tunel1_4),
                connectsT city1 city6 tunel1_6,
                connectsT city6 city1 tunel1_6,
                True]

testUsesT = [usesT link2_3 tunel1_6,
            not (usesT link5_6 tunel1_4),
            True]

testDelayT = [delayT tunel1_4 == sum (map delayL [link1_2, link2_3, link3_4]),
             True
             ]

--Pruebas de region:
--Caso simple: Se crea una región vacía y se agregan ciudades, links y túneles. Funcionamiento normal.
region1 = newR
cities1to6 = [city1, city2, city3, city4, city5, city6]
regionWith6Cities = addCitiesR region1 cities1to6
regionWith5Links = addLinksR regionWith6Cities [(city1, city2), (city2, city3), (city3, city4), (city4, city5), (city5, city6)] [quality1, quality2, quality1, quality3, quality0]
simpleRegion = tunelR regionWith5Links [city1, city2, city3, city4, city5, city6]


--Caso complejo: Se añaden más túneles a la región simple. No genera excepciones
complexRegion = addTunelsR simpleRegion [[city1, city2, city3], [city3, city4, city5]]

testConnectedR = [connectedR simpleRegion city1 city6,
                  connectedR simpleRegion city6 city1,
                  not (connectedR simpleRegion city1 city5),
                  not (connectedR simpleRegion city1 city2),
                  True]

testLinkedR = [linkedR simpleRegion city1 city2,
                linkedR simpleRegion city2 city1,
                not (linkedR simpleRegion city1 city5),
                not (linkedR simpleRegion city1 city6),
                True]

invalidDelay = testF (delayR simpleRegion city1 city4) -- Pedir el delay de un link que no existe.

testDelayR = [(delayR simpleRegion city1 city6 - sum (map delayL [link1_2, link2_3, link3_4, link4_5, link5_6])) < 0.01, --No funciona precisamente "==" con floats
                invalidDelay,
                True]

testAvailableCapacityR = [availableCapacityForR simpleRegion city1 city2 == 2,
                          availableCapacityForR simpleRegion city2 city3 == 6,
                          True]

--Casos de excepciones:
duplicatedCity = addCitiesR complexRegion [city1, city2] -- Agregar una ciudad que ya existe en la region

sameLocationCity = addCitiesR complexRegion [city6'] -- Agregar una ciudad con la misma ubicación que otra ya existente.

notExistenteCity = addLinksR complexRegion [(city6, city7)] [quality1] -- Hacer un link con una ciudad que no pertenece a la región.

duplicatedLink = addLinksR complexRegion [(city1, city2), (city2, city3)] [quality1, quality2] -- Agregar un link ya existente en la región, aunque sus calidades sean distintas.

selfLink = addLinksR complexRegion [(city1, city1)] [quality1] -- Enlazar una ciudad con sí misma.

notConnectedCities = addTunelsR complexRegion [[city1, city4, city5]] -- Crear un túnel con ciudades que no están conectadas.

emptyCityList = tunelR complexRegion [] -- Agregar un tunel con una lista vacía de ciudades.

fullCapacity = addTunelsR complexRegion [cities1to6, cities1to6, cities1to6]  -- Agregar un link que excede la capacidad disponible.


testErrorsR = map testF [duplicatedCity,
                        sameLocationCity,
                        notExistenteCity,
                        duplicatedLink,
                        selfLink,
                        notConnectedCities,
                        emptyCityList,
                        fullCapacity]