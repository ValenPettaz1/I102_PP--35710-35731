module TestsZone where

import Point
import Link
import City
import Quality
import Tunel
import Region
import Control.Exception ( SomeException, evaluate, tryJust ) 
import System.IO.Unsafe ( unsafePerformIO )

addCitiesR :: Region -> [City] -> Region
addCitiesR region [] = region
addCitiesR region cities = foldl foundR region cities 

addLinksR :: Region -> [(City, City)] -> [Quality] -> Region
addLinksR region [] [] = region
addLinksR region (par:cities) (qlty:qlties) = addLinksR (linkR region (fst par) (snd par) qlty) cities qlties

addTunelsR :: Region -> [[City]] -> Region
addTunelsR region []  = region
addTunelsR region (list:cities) = addTunelsR (tunelR region list) cities

--Prueba de puntos:
point1 = newP 100 250
point2 = newP (-150) 250

testP = [difP point1 point2 == 250.0,
        difP point2 point1 == 250.0,
        True]

--Prueba de ciudades:

city1 = newC "San Nicolas" (newP 100 250)
city2 = newC "Sarmiento" (newP (-150) 250)
city3 = newC "Buenos Aires" (newP 0 0)
city4 = newC "La Pampa" (newP 100 0)
city5 = newC "Antartida" (newP 0 (-1000))
city6 = newC "Jujuy" (newP 1000 1000)

   
testC = [distanceC city1 city2 == 250.0,
        distanceC city2 city1 == 250.0,
        nameC city3 == "Buenos Aires",
        True]

--Prueba de calidad:

quality1 = newQ "Baja" 3 0.1
quality2 = newQ "Media" 7 0.05
quality3 = newQ "Alta" 10 0.01

testQ = [capacityQ quality1 == 3,
        delayQ quality1 == 0.1, 
        True] 

invQuality1 = newQ "Baja" (-3) 0.1
invQuality2 = newQ "" 3 0.1
invQuality3 = newQ "Media" 3 (-0.1)
invQuality4 = newQ "" (-3) (-0.1)

--Prueba de links:

link1_2 = newL city1 city2 quality1
link2_3 = newL city2 city3 quality2
link3_4 = newL city3 city4 quality1
link4_5 = newL city4 city5 quality3
link5_6 = newL city5 city6 quality2

testL = [connectsL city3 link2_3,
        linksL city1 city2 link1_2,
        capacityL link2_3 == 7,
        delayL link1_2 == 250 * 0.1,
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

testDelayT = [delayT tunel1_4 == sum [delayL link1_2, delayL link2_3, delayL link3_4],
             True
             ]

--Prueba de region:
regionVoid = newR
regionTest = newR

regionCities1_6 = addCitiesR regionVoid [city1, city2, city3, city4, city5, city6]
regionCities1_4 = addCitiesR regionTest [city1, city2, city3, city4]

regionLink1_2 = linkR regionCities1_6 city1 city2 quality1
regionLink2_3 = linkR regionLink1_2 city2 city3 quality2
regionLink3_4 = linkR regionLink2_3 city3 city4 quality1

regionTunel1_4 = tunelR regionLink3_4 [city1, city2, city3, city4, city5, city6]
regionTunel1_4y1_2 = tunelR regionTunel1_4 [city1, city2]
regionTunel1_4y1_2x2 = tunelR regionTunel1_4y1_2 [city1, city2]
regionTunel1_4y1_2x3 = tunelR regionTunel1_4y1_2x2 [city1, city2]

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