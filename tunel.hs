module Tunel ( Tunel, newT, connectsT, usesT, delayT)
   where

import Point ( newP )
import Quality ( newQ )
import Link ( Link, newL, connectsL, delayL )
import City ( City, newC )

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

getLinksT :: Tunel -> [Link]
getLinksT (Tun link) = link

linksUsedByCity :: City -> Tunel -> [Link]
linksUsedByCity city tunel = [link | link <- linklist, connectsL city link] where
      linklist = getLinksT tunel

extremeCities :: City -> Tunel -> Bool
extremeCities city tunel | length (linksUsedByCity city tunel) == 1 = True
                         | otherwise = False

connectsT :: City -> City -> Tunel -> Bool -- indica si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 tunel = extremeCities city1 tunel && extremeCities city2 tunel 

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link tunel = link `elem` getLinksT tunel

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT tunel = sum (map delayL (getLinksT tunel))

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

testConnectsT = [connectsT city1 city2 tunel1_4,
                connectsT city4 city3 tunel1_4,
                connectsT city1 city6 tunel1_6,
                connectsT city6 city1 tunel1_6,
                True]

testUsesT = [usesT link2_3 tunel1_6,
            not (usesT link5_6 tunel1_4),
            True]

testDelayT = [delayT tunel1_4 == sum [delayL link1_2, delayL link2_3, delayL link3_4],
             True
             ]


