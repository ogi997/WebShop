SELECT av.fk_proizvod, count(pr.id) as numOfProduct FROM product pr
JOIN attribute_value av ON pr.id = av.fk_proizvod
where pr.fk_category = 1 AND av.fk_attribute_id IN (1, 2, 3) AND av.value IN ('Stan', '2', '75')
GROUP BY av.fk_proizvod
having numOfProduct = 3;