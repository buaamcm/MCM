function [ s ] = check_solution( grid )
a = 0;
b = 0;
c = 0;
A = zeros(1, 9);
B = zeros(1, 9);
for i = 1 : 1 : 9
    for j = 1 : 1 : 9
        if A(1, grid(i, j)) == 0 
            A(1, grid(i, j)) = 1;
        else
            break;
        end
    end
    if sum(A) == 9
        B(1, i) = 1;
    else
        break;
    end
    A = zeros(1, 9);
end
if sum(B) == 9
    a = 1;
end

B = zeros(1, 9);

for j = 1 : 1 : 9
    for i = 1 : 1 : 9
        if A(1, grid(i, j)) == 0
            A(1, grid(i, j)) = 1;
        else
            break;
        end
    end
    if sum(A) == 9
        B(1, j) = 1;
    else
        break
    end
    A = zeros(1, 9);
end
if sum(B) == 9
    b = 1;
end

B = zeros(1, 9);

for i = 1 : 1 : 3
    for j = 1 : 1 : 3
        for k = 1 : 1 : 9
            if A(1, grid(3 * (i - 1) + ceil(k / 3), 3 * (j - 1) + k - (ceil(k / 3) - 1) * 3)) == 0
                A(1, grid(3 * (i - 1) + ceil(k / 3), 3 * (j - 1) + k - (ceil(k / 3) - 1) * 3)) = 1;
            else
                break;
            end
        end
        if sum(A) == 9
            B(1, 3 * (i - 1) + j) = 1;
        else
            break;
        end
        A = zeros(1, 9);
    end
end
if sum(B) == 9;
    c = 1;
end

s = a && b && c;
            
end

