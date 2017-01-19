function [ grid ] = root_solution( x )
grid = zeros(9, 9);
for i = 1 : 1 : 3
    for j = 1 : 1 : 3
        for k = 1 : 1 : 9
            grid(3 * (i - 1) + j, k) = mod(x, 9) + 1;
            x = x + 1;
        end
        x = x + 3;
    end
    x = x + 1;
end


end

