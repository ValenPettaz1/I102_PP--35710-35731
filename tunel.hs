module Tunel ( Tunel, newT, connectsT, usesT, delayT)
   where

import Point
import Quality
import Link
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT = Tun

getLinksT :: Tunel -> [Link]
getLinksT (Tun link) = link

getExtremeLinksT :: Tunel -> [Link]
getExtremeLinksT tunel = [head linklist, last linklist] where
      linklist = getLinksT tunel

extremeCities :: City -> Tunel -> Bool
extremeCities city tunel = connectsL city (head extremeLinklist) || connectsL city (last extremeLinklist) where
      extremeLinklist = getExtremeLinksT tunel

connectsT :: City -> City -> Tunel -> Bool -- indica si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 tunel = extremeCities city1 tunel && extremeCities city2 tunel

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link tunel = link `elem` getLinksT tunel

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT tunel = sum (map delayL (getLinksT tunel))

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

testConnectsT = [connectsT city1t city2t tunel1_4t,
                connectsT city4t city3t tunel1_4t,
                connectsT city1t city6t tunel1_6t,
                connectsT city6t city1t tunel1_6t,
                True]

testUsesT = [usesT link2_3t tunel1_6t,
            not (usesT link5_6t tunel1_4t),
            True]

testDelayT = [delayT tunel1_4t == sum [delayL link1_2t, delayL link2_3t, delayL link3_4t],
             True
             ]


