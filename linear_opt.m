function [x, fval] = linear_opt(VN, ALPHA, BETA, VMax, Emin, VUmin, VUMax, VEMax,  VDMax)
%VN is V_{n,i} vector
%ALPHA, BETA   
%VMax, Emin
%VUmin is V_{u, i}^{min} vector
%VUMax is V_{u, i}^{max} vector
%VEMax is V_{e, i}^{max} vector
%VDMax is V_{D, i}^{max} vector

%我们按照V_{D, i}, V_{e, i}, V_{u, i}, dV_{i}
%的顺序排列变量

n = length(VN);

%容量变化上下界
VALPHA = VMax.*ALPHA;
VBETA = VMax.*BETA;
%构造不等约束A，B
% \sum V_{e, i} >= E_{min}，转化为小于问题
A = zeros(1, 4 * n);
for i = n + 1 : 2 * n
    A(i) = -1;
end

b = -Emin;

%构造等式约束
%变量间的关系约束，矩阵的列数为4n，行数为n
%i = 1情况特殊
Aeq = zeros(n, 4 * n);
%V_{D, 1}
Aeq(1, 1) = 1;
%V_{e, 1}
Aeq(1, n + 1) = 1;
%V_{u, 1}
Aeq(1, 2 * n + 1) = 1;
%dV_{1}
Aeq(1, 3 * n + 1) = 1;
for i = 2 : n
    %V_{D, i - 1}, V_{D, i}
    Aeq(i, i - 1) = -1;
    Aeq(i, i) = 1;
    %V_{e, i - 1}, V_{e, i}
    Aeq(i, n + i - 1) = -1;
    Aeq(i, n + i) = 1;
    %V_{u, i}
    Aeq(i, 2 * n + i) = 1;
    %dV_{i}
    Aeq(i, 3 * n + i) = 1;
end

beq = VN;

%构造上下界约束
%下界
%排水量约束，发电约束，其他用水约束, 相对变化约束
lb = [zeros(1, n) zeros(1, n) VUmin VALPHA];
%上届
ub = [VDMax VEMax VUMax VBETA];

%调用linprog
f = zeros(1, 4 * n);
f(n) = 1;
[x, fval] = linprog(f, A, b, Aeq, beq, lb, ub)

end

